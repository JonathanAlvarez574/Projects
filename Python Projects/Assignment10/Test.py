def recursive_factorial(n):
    if n == 0:
        return 1
    else:
        return n * recursive_factorial(n-1)


def factorialI(n):
    factorial = 1
    for i in range(n):
        factorial *= i + 1
    return factorial

print(recursive_factorial(9))
print(factorialI(9))