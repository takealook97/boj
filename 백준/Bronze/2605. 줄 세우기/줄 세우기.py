N = int(input())
arr = []
order = list(map(int, input().split()))
num = 1
for i in range(N):
    arr.insert(order[i], num)
    num += 1
for i in arr[::-1]:
    print(i, end=' ')
