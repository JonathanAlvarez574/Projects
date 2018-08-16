# Assignment 9                                                  Due 6/22/18
# CS 3A
# Name:  Jonathan Alvarez Student ID: 20190729
# Part 1:
# We want to know how fast Sample Answer 2 runs without any benchmark code.
# Also Fix any errors in code.
# ---------------------- Part 1Source Code --------------------------------------------


def makeDict(input_file_name):
    with open(input_file_name, 'r', encoding='utf-8') as f:
        dict_list = f.readlines()
        index = 0
        while index < len(dict_list):
            dict_list[index] = dict_list[index].rstrip('\n')
            index += 1
    keys = (x for x in range(0, 999))
    return dict(zip(keys, dict_list))


en_dict = makeDict("1kwords.en.txt")
sp_dict = makeDict("1kwords.sp.txt")
gr_dict = makeDict("1kwords.gr.txt")

dict_language = {"en": en_dict, "sp": sp_dict, "gr": gr_dict}


def translate(fm, to, word):
    if fm not in dict_language.keys():
        return "from is not a valid language code."
    elif to not in dict_language.keys():
        return "to not a valid language code."
    else:
        for i in range(0, 999):
            if fm == to:
                return word
            if word not in dict_language[fm].values():
                return "The word you want to look up is not in the dictionary."
            if dict_language[fm][i] == word:
                return dict_language[to][i] # Error found here, return was commented out thus not printing right answer


print(translate("en", "gr", "light"))
print(translate("en", "sp", "light"))
print(translate("sp", "en", "momento"))
print(translate("sp", "gr", "momento"))
print(translate("gr", "en", "πολλαπλασιάστε"))
print(translate("gr", "sp", "πολλαπλασιάστε"))

print(translate("en", "gr", "giraffe"))
# ---------------------------------- Part 1 Run -------------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment9/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment9/Assignment9_1.py
# φως
# luz
# moment
# στιγμή
# multiply
# multiplicar
# The word you want to look up is not in the dictionary.

# ---------------------------------- End of Part 1 ------------------------------------------