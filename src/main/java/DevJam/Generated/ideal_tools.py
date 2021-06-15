import requests
from bs4 import BeautifulSoup


def format_java_material(block_name):
    if '(' in block_name:
        block_name = block_name[:block_name.index('(') - 1]
    if 'Block_of_' in block_name:
        block_name = block_name[block_name.index('Block_of_') + len('Block_of_'):] + '_Block'
    block_name = block_name.replace('%27', '_')
    return block_name.upper()


with open('IdealToolsGenerated.java', 'w') as file:
    file.write('package DevJam.Generated;\n')
    file.write('\n')
    file.write('import DevJam.Enums.EquipmentType;\n')
    file.write('import org.bukkit.Material;\n')
    file.write('\n')
    file.write('import java.util.HashMap;\n')
    file.write('\n')
    file.write('public class IdealToolsGenerated {\n')
    file.write('    public static final HashMap<Material, EquipmentType> IDEAL_TOOLS = new HashMap<Material, EquipmentType>() {{\n')
    file.write('        // TODO - manually comb through and prune\n')

    URL = 'https://minecraft.fandom.com/wiki/Cobweb'
    page = requests.get(URL)
    soup = BeautifulSoup(page.content, 'html.parser')
    tableDiv = soup.find(class_='overflow-x: hidden;')  # Div of the table containing all blocks in the game
    links = tableDiv.find_all('a')
    blocks = []
    for link in links:
        if link.get('class') is None:
            block_name = link.get('href')[6:]
            if '#' in block_name or 'Edition' in block_name or block_name in blocks:
                continue
            blocks.append(block_name)

            # Get ideal tool
            URL = 'https://minecraft.fandom.com/wiki/' + block_name
            page = requests.get(URL)
            soup = BeautifulSoup(page.content, 'html.parser')
            tableHeaders = soup.find_all('th')
            tool = ''
            for tableHeader in tableHeaders:
                if 'Tool' in tableHeader.text:
                    td = str(tableHeader.parent.find('td')).lower()
                    if 'shovel' in td:
                        tool = 'SHOVEL'
                    elif 'pickaxe' in td:
                        tool = 'PICKAXE'
                    elif 'axe' in td:
                        tool = 'AXE'
                    elif 'hoe' in td:
                        tool = 'HOE'
                    break

            if tool != '':
                file.write('        put(Material.')
                file.write(format_java_material(block_name))
                file.write(', EquipmentType.')
                file.write(tool)
                file.write(');\n');

    file.write('    }};\n')
    file.write('}')
    file.close()