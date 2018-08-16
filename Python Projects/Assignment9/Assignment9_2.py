# Assignment 9                                                                 Due: 6/22/18
# CS 3A
# Name:  Jonathan Alvarez Student ID: 20190729
# Part 2-3:
# Modify our existing code of Sample Answer 2 to add benchmark code.
# ---------------------------  Part 2-3 Source Code -------------------------------------------

from timeit import default_timer as timer


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
                return dict_language[to][i]  # Error found here, return was commented out thus not printing right answer


def func_to_measure():
    spbee = translate("gr","sp", "μέλισσα")
    translate("sp","en", spbee)
    translate("en","sp", "heart")
    translate("en","gr", "heart")
    translate("en","gr", "diegetic")
    translate("gr", "en", "μέλισσα")
    spneck = translate("en", "sp", "neck")
    translate("en", "gr", "neck")
    translate("sp", "gr", spneck)
    translate("en", "sp", "beauty")


print("starting timer")
start = timer()
for i in range(10000):
    func_to_measure()
end = timer()

elapsed = end - start
print("10K words translated in Sample #2:", elapsed, "secs")

# -------------------------- Part 2-3 Run -----------------------------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment9/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment9/Assignment9_2.py
# starting timer
# 10K words translated in Sample #2: 320.9414033240173 secs