WITH FRONT AS (
    SELECT *
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
)

SELECT DISTINCT D.ID ID, D.EMAIL EMAIL, D.FIRST_NAME FIRST_NAME, D.LAST_NAME LAST_NAME
FROM DEVELOPERS D
JOIN FRONT F
ON (D.SKILL_CODE & F.CODE) = F.CODE
ORDER BY ID;
