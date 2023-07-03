N, K = map(int, (input()).split())
boy = [0] * 6
girl = [0] * 6
for i in range(N):
    S, Y = map(int, (input()).split())
    if S == 0:
        girl[Y - 1] += 1
    else:
        boy[Y - 1] += 1

count = int(0)
for i in range(6):
    count += (boy[i] // K)
    count += (girl[i] // K)
    if boy[i] % K != 0:
        count += 1
    if girl[i] % K != 0:
        count += 1

print(count)
