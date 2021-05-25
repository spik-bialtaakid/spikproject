anot_path = r'D:\Project\spik\spikproject\dt\anot'
def create_labelmap(path):
    labels = [{'name': 'A', 'id':1}]
    with open(path + '\label_map.pbtxt', 'w') as f:
        for label in labels:
            f.write('item { \n')
            f.write('\tname:\'{}\'\n'.format(label['name']))
            f.write('\tid:{}\n'.format(label['id']))
            f.write('}\n')


create_labelmap(anot_path)