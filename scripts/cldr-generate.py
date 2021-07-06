from mako.template import Template
import argparse, json

parser = argparse.ArgumentParser()
parser.add_argument('t', type=argparse.FileType('r'), help="File with Mako template")
parser.add_argument('json', type=argparse.FileType('r'), help="File with JSON data")
args = parser.parse_args()

json_data = None
with open(args.json) as file:
	json_data = json.load(json_file)

template_data = None
with open(args.t) as file:
	template_data = f.read()

for lang, lang_data in json_data.items():
	for code, code_data in lang_data:
    	new_file_name = args.t.name.replace(".template", lang)
    	new_file_contents = Template(template_data).render(data=code_data, code=code, lang=lang)
    	print(f"Creating {new_file_name}")
    	with open(new_file_name, "w") as file_output:
    		file_output.write(new_file_contents)