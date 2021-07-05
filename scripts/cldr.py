import requests, re, json
from bs4 import BeautifulSoup

URL_CLDR_BASE = "https://unicode-org.github.io/cldr-staging/charts/37/supplemental"
URL_CLDR_DATA = f"{URL_CLDR_BASE}/language_plural_rules.html"
URL_CLDR_VERSION = f"{URL_CLDR_BASE}/include-version.html"
HEADERS = {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Max-Age': '3600',
    'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0'
    }

RE_LINK_TO_ANOTHER_LANG = re.compile('^=(\w+)$')

def load_page_soup(url): return BeautifulSoup(requests.get(url, HEADERS).content, 'html.parser')

def parse_rowspan(td):
	rowspan_size = td.get("rowspan")
	if rowspan_size is None: return 1
	return int(rowspan_size)

class ParsedCldr:
	version = ""
	table = []
	max_cols = None
	languages = {}
	equal_languages = {}

	def load_version(self, version_soup): self.version = version_soup.find("span", class_="version").get_text()

	def set_value(self, row_index, col_index, value):
		while len(self.table) <= row_index:
			self.table.append([None] * self.max_cols)
		while self.table[row_index][col_index] is not None:
			col_index += 1
		self.table[row_index][col_index] = value

	def load_data(self, tds, row_index):
		if self.max_cols is None: self.max_cols = len(tds)
		col_index = 0
		for td in tds:
			while len(self.table) <= row_index:
				self.table.append([None] * self.max_cols)
			while self.table[row_index][col_index] is not None: col_index += 1
			data = td.get_text()
			if "n/a" in data or "Not available" in data: data = None
			rowspan = parse_rowspan(td)
			if rowspan > 1:
				for row_span_index in range(0, rowspan):
					self.set_value(row_index + row_span_index, col_index, data)
			else:
				self.set_value(row_index, col_index, data)
			col_index += 1

	def set_language_equal(self, lang_source, lang_to):
		if lang_source not in self.equal_languages: self.equal_languages[lang_source] = []
		self.equal_languages[lang_source].append(lang_to)

	def build_data(self):
		for row in self.table:
			language_name = row[0]
			if language_name not in self.languages: self.languages[language_name] = {}
			record = self.languages[language_name]
			language_code = row[1]
			if language_code not in record: record[language_code] = {}
			record = record[language_code]
			language_type = row[2]
			language_mode_match = RE_LINK_TO_ANOTHER_LANG.match(language_type)
			if language_mode_match is not None:
				self.set_language_equal(language_mode_match.group(1), language_code)
				continue
			if language_type not in record: record[language_type] = {}
			record = record[language_type]
			language_category = row[3]
			if language_category not in record: record[language_category] = {}
			record = record[language_category]
			record.update({ "examples": row[4], "pairs": row[5], "rules": row[6] })

	def to_json(self): return json.dumps({"version": self.version, "languages": self.languages, "equals": self.equal_languages})

def main():
	parsed_cldr = ParsedCldr()
	#get version
	version_soup = load_page_soup(URL_CLDR_VERSION)
	parsed_cldr.load_version(version_soup)
	#get languages data
	languages_soup = load_page_soup(URL_CLDR_DATA)
	trs_cldtr = languages_soup.find("table", class_="dtf-table").find_all("tr")
	row_index = 0
	for tr in trs_cldtr:
		tds_cldtr = tr.find_all("td", class_="dtf-s")
		if len(tds_cldtr):
			parsed_cldr.load_data(tds_cldtr, row_index)
			row_index += 1
	parsed_cldr.build_data()
	print(parsed_cldr.to_json())

if __name__ == '__main__': main()