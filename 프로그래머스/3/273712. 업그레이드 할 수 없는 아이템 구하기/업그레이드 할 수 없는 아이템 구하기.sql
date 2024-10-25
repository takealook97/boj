SELECT T1.ITEM_ID AS ITEM_ID, I.ITEM_NAME AS ITEM_NAME, I.RARITY AS RARITY
FROM ITEM_TREE T1
JOIN ITEM_INFO I
ON T1.ITEM_ID = I.ITEM_ID
WHERE NOT EXISTS (
    SELECT 1
    FROM ITEM_TREE T2
    WHERE T1.ITEM_ID = T2.PARENT_ITEM_ID
)
ORDER BY ITEM_ID DESC;
