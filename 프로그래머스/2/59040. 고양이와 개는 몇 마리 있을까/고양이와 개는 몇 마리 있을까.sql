SELECT ANIMAL_TYPE, COUNT(ANIMAL_TYPE) count
FROM ANIMAL_INS
WHERE ANIMAL_TYPE IN ('Dog', 'Cat')
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE;