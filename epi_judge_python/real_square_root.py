from test_framework import generic_test


def square_root(x):
    # TODO - you fill in here.
    if x < 1.0:
        low = x
        high = 1.0
    else:
        low = 1.0
        high = x

    while low < high:
        mid = low + (high - low) * 0.5
        print("low: {}, mid: {}, high: {}".format(low, mid, high))
        if mid*mid > x:
            high = mid 
        elif mid*mid < x:
            low = mid 
        elif mid*mid == x:
            return mid

    return low


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main("real_square_root.py",
                                       'real_square_root.tsv', square_root))
