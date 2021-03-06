import sys
import random


# ---------------- DEFINITIONS & CLASS DECLARATION ----------------------------------------
class TripleString:
    """ The Three String class """
    # intended class constants ------------------------------------
    MIN_LEN = 1
    MAX_LEN = 50
    DEFAULT_STRING = "(undefined)"
    global Mult_Cherries_Count
    Mult_Cherries_Count = []

    # --------------------- constructor method ------------------------------------
    def __init__(self, string1=DEFAULT_STRING, string2=DEFAULT_STRING, string3=DEFAULT_STRING):
        self.set_string1(string1)
        self.set_string2(string2)
        self.set_string3(string3)

    # ----------------- mutator ("set") methods -------------------------------
    def set_string1(self, string1):
        if (self.valid_string(string1)):
            self.string1 = string1
            return True
        return False

    def set_string2(self, string2):
        if (self.valid_string(string2)):
            self.string2 = string2
            return True
        return False

    def set_string3(self, string3):
        if (self.valid_string(string3)):
            self.string3 = string3
            return True
        return False

    # accessor ("get") methods -------------------------------
    def get_string1(self):
        return self.string1

    def get_string2(self):
        return self.string2

    def get_string3(self):
        return self.string3

    # helper methods for entire class -----------------
    def valid_string(self, the_str):
        the_str_len = len(str(the_str))
        if (the_str_len >= self.MIN_LEN and the_str_len <= self.MAX_LEN):
            return True
        return False

    def to_string(self):
        str = (self.string1 + ", "
               + self.string2 + ", "
               + self.string3)
        return str


# ------------- GLOBAL SCOPE METHODS --------------------------------------------------
Chance_of_bar = 38
CHANCE_CHERRIES = 40
Chance_of_Space = 7
Chance_of_seven = 15

Mult_Single_Cherries = 5
Mult_Double_Cherries = 15
Mult_Cherries = 30
Mult_Bar = 50
Mult_Seven = 100

Slot_Bar = "BAR"
Slot_Cherries = "cherries"
Slot_Space = "space"
Slot_Seven = "7"

print("Hello today we are going to be simulating a slot machine!")

def get_bet():
    """ grabs the users input for bet amount """
    while (True):
        bet = int(input("(To exit please enter 0)" + "\nPlease enter a bet between $1 - $50: "))
        if 0 < bet <= 50:
            return bet
        elif bet == 0:
            sys.exit("Thanks for playing slots")
        print("Please enter a valid number between $0 and $50\n")


def pull():
    """ This returns the TripleString class with random strings """
    return TripleString(rand_string(), rand_string(), rand_string())


def rand_string():
    """ This generates a random slot strings """
    rand_num = random.randrange(1, 100)
    if 0 < rand_num <= Chance_of_Space:
        return Slot_Space
    elif Chance_of_Space < rand_num <= Chance_of_Space + Chance_of_seven:
        return Slot_Seven
    elif Chance_of_Space + Chance_of_seven < rand_num <= Chance_of_Space + Chance_of_seven + Chance_of_bar:
        return Slot_Bar
    elif Chance_of_Space + Chance_of_seven + Chance_of_bar < rand_num <= 100:
        return Slot_Cherries
    else:
        return "Error!"


def get_pay_multiplier(reels):
    """ This determines the multiplier based on the TripleString object """
    slot1 = reels.get_string1()
    slot2 = reels.get_string2()
    slot3 = reels.get_string3()
    if slot1 == slot2 == slot3:
        if slot1 == Slot_Cherries:
            Mult_Cherries_Count.append(1)
            return Mult_Cherries
        elif slot1 == Slot_Bar:
            return Mult_Bar
        elif slot1 == Slot_Seven:
            return Mult_Seven
    elif slot1 == Slot_Cherries and slot2 != Slot_Cherries:
        return Mult_Single_Cherries
    elif slot1 == slot2 == Slot_Cherries and slot3 != Slot_Cherries:
        return Mult_Double_Cherries
    else:
        return 0


def display(reels, winnings):
    """ This Displays the reels and winnings """
    multiplier = get_pay_multiplier(reels)
    total_winnings = winnings * multiplier
    print(reels.to_string())
    if (total_winnings == 0):
        print("Sorry, you lose\n")
    else:
        print("Congratulations, you win: $" + str(total_winnings) + "\n")


# ------------- MAIN --------------------------------------------------
# bet = 1
# while (bet != 0):
#     bet = get_bet()
#     reels = pull()
#     display(reels, bet)
i = 1
bet = get_bet()

while i <= 100000:
    try:
        reels = pull()
        display(reels, bet)
        i = i + 1
    except TypeError:
        TypeError
        continue

print(Mult_Cherries_Count.count(1))
# ----------- RUN ----------------------
# Hello today we are going to be simulating a slot machine!
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, BAR, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# 7, cherries, 7
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, BAR, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# 7, BAR, BAR
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, BAR, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, 7, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, cherries
# Congratulations, you win: $210
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, space, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, cherries
# Congratulations, you win: $210
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, cherries, cherries
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, BAR, BAR
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, cherries
# Congratulations, you win: $210
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, cherries, cherries
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# space, BAR, cherries
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, space
# Congratulations, you win: $105
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, BAR, BAR
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# 7, cherries, cherries
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, 7, BAR
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 77
# Please enter a valid number between $0 and $50
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, cherries, cherries
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 77
# Please enter a valid number between $0 and $50
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, cherries, BAR
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, cherries, space
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, space, BAR
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# 7, cherries, 7
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, cherries
# Congratulations, you win: $210
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, BAR
# Congratulations, you win: $105
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, BAR
# Congratulations, you win: $105
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, cherries
# Congratulations, you win: $210
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 77
# Please enter a valid number between $0 and $50
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, BAR, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# BAR, cherries, BAR
# Sorry, you lose
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, 7, cherries
# Congratulations, you win: $35
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 7
# cherries, cherries, BAR
# Congratulations, you win: $105
#
# (To exit please enter 0)
# Please enter a bet between $1 - $50: 0
# Thanks for playing slots
