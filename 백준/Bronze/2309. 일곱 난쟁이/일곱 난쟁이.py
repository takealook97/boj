arr = [0] * 9
total = 0
for i in range(9):
    arr[i] = int(input())
    total += arr[i]
for i in range(0, 8, 1):
    flag = False
    for j in range(i + 1, 9, 1):
        num = arr[i] + arr[j]
        if total - int(num) == 100:
            a = arr[i]
            b = arr[j]
            arr.remove(a)
            arr.remove(b)
            flag = True
            break
    if flag:
        break
arr.sort()
for i in arr:
    print(i)
