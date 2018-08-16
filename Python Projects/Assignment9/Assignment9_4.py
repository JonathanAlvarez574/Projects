# Assignment 9                                                                 Due: 6/22/18
# CS 3A
# Name:  Jonathan Alvarez Student ID: 20190729
# Part 4:
# Relate the speed results to the speed results from
# Sample Answer 4 vs Sample 2
# ---------------------------  Part 4 Source Code -------------------------------------------
"""Sample #2 translate the 1000 most common words into English, Spanish, Greek"""
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


'''Sample #4 translate the 1000 most common words into English, Spanish, Greek, '''

from timeit import default_timer as timer

with open('1kwords.en.txt', encoding='utf-8') as f:
    enl = f.read().splitlines()

with open('1kwords.sp.txt', encoding='utf-8') as f:
    spl = f.read().splitlines()

with open('1kwords.gr.txt', encoding='utf-8') as f:
    grl = f.read().splitlines()


def translate(fm, to, word):
    if   fm == "en": d = den
    elif fm == "sp": d = dsp
    else           : d = dgr

    if   to == "en": inx = 0
    elif to == "sp": inx = 1
    else           : inx = 2
    try:
        translation = d[word][inx]
    except KeyError:
        translation = ""   # "<no word>"

    # print("translate(",fm,to,word, ") is ",translation)
    return translation


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

wlist = list(zip(enl, spl, grl))

den = { } # empty dict
dsp = { }
dgr = { }

#  you look it up in English to get the key
#  when you have the key, look it up in the dict for that lang.

for t in wlist:
    den[t[0]] = ("", t[1], t[2])
    dsp[t[1]] = (t[0], "", t[2])
    dgr[t[2]] = (t[0],t[1], "")

print("starting timer")
start = timer()
for i in range(10000):
    func_to_measure()
end = timer()

elapsed = end - start
print("10K words translated from Sample #4 is:", elapsed, "secs")
# print("1000 words takes", elapsed/10000, "secs")

# ------------------------ Part 4 Run/ Explaination --------------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment9/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment9/Assignment9_4.py
# starting timer
# 10K words translated in Sample #2: 322.1347387559945 secs
# starting timer
# 10K words translated from Sample #4 is: 0.03856243100017309 secs
#
# Percentage difference:
# 199.9516 %
#
# Difference:
# 322.091
#
# Explanation: The main reason that the first run took a lot longer than run two is because there is a lot of things
# being proceed within the translation function also without any exception handling this will result with longer
# execution time. Another reason that the second runs faster is because the making of the dictionaries is a lot more
# simplistic and more through which will result in less of a wait time for the code to process. Another reason I know
# that run 1 is slower is because the translation function is defined well and which works together with the
# dictionary making and implementing since these are poorly written it will result in a vastly longer compile time.
# Finally the way the first one is written is has a lot of loop iterations, print statements, list traversal which
# are written poorly that makes the program run a lot slower.
#
