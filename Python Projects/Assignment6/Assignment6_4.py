# -------------------------- Assignment 6 Part 4------------------------------------
# Assignment 6 by Jonathan Alvarez                  Due: 6/10/2018
# This is a four part assignment:
# Part 2:
# Translate using dictionary
## -------------------------- Part 4 Code ----------------------------------------
english_list = ["bee", "iguana", "scorpion", "giraffe", "spider"]
spanish_list = ["abeja","iguana","alacrán","jirafa","araña"]
greek_list = ["μέλισσα", "ιγκουάνα", "σκορπιός", "καμηλοπάρδαλη", "αράχνη"]

english_to_spanish = dict(zip(english_list, spanish_list))
spanish_to_english = dict(zip(spanish_list, english_list))

english_to_greek = dict(zip(english_list, greek_list))
greek_to_english = dict(zip(greek_list, english_list))

greek_to_spanish = dict(zip(greek_list, spanish_list))
spanish_to_greek = dict(zip(spanish_list, greek_list))

def translate(fm, to, word):
    try:
# English to Spanish
        if (fm == "en") and (to == "sp"):
            translation = english_to_spanish.get(word)
            if translation:
                return word + " (" + fm + ")" + " = " + "(" + to + ") " + translation
# Spanish to English
        if (fm == "sp") and (to == "en"):
            translation = spanish_to_english.get(word)
            if translation:
                return word + " (" + fm + ")" + " = " + "(" + to + ") " + translation
#  English to Greek
        if (fm == "en") and (to == "gr"):
            translation = english_to_greek.get(word)
            if translation:
                    return word + " (" + fm + ")" + " = " + "(" + to + ") " + translation
# Greek to English
        if (fm == "gr") and (to == "en"):
            translation = greek_to_english.get(word)
            if translation:
                return word + " (" + fm + ")" + " = " + "(" + to + ") " + translation
# Spanish to Greek
        if (fm == "sp") and (to == "gr"):
            translation = spanish_to_greek.get(word)
            if translation:
                return word + " (" + fm + ")" + " = " + "(" + to + ") " + translation
# Greek to Spanish
        if (fm == "gr") and (to == "sp"):
            translation = greek_to_spanish.get(word)
            if translation:
                return word + " (" + fm + ")" + " = " + "(" + to + ") " + translation
    except KeyError:
        pass

print("Today we are going to be translating some words!\n")

Test = translate("en", "sp", "bee")
print(Test)

Test = translate("en", "gr", "bee")
print(Test)

Test = translate("sp", "en", "araña")
print(Test)

Test = translate("sp", "gr", "araña")
print(Test)

Test = translate("gr", "en", "ιγκουάνα")
print(Test)

Test = translate("gr", "sp", "ιγκουάνα")
print(Test)

Test = translate("en", "sp", "scorpion")
print(Test)

Test = translate("en", "gr", "scorpion")
print(Test)

Test = translate("gr", "sp", "αράχνη")
print(Test)

Test = translate("en", "gr", "giraffe")
print(Test)

Test = translate("en", "gr", "cow")
print(Test)







## --------------------------------- Part 4 Output -------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment6/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment6/venv/Assignment6_4.py
# Today we are going to be translating some words!
#
# bee (en) = (sp) abeja
# bee (en) = (gr) μέλισσα
# araña (sp) = (en) spider
# araña (sp) = (gr) αράχνη
# ιγκουάνα (gr) = (en) iguana
# ιγκουάνα (gr) = (sp) iguana
# scorpion (en) = (sp) alacrán
# scorpion (en) = (gr) σκορπιός
# αράχνη (gr) = (sp) araña
# giraffe (en) = (gr) καμηλοπάρδαλη
# None
