WITH PYTHON AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'Python'
), 
FRONT AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
), 
B AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'C#'
)
SELECT
    CASE
        WHEN (D.SKILL_CODE & P.CODE) = P.CODE 
             AND EXISTS (
                 SELECT 1 
                 FROM FRONT F 
                 WHERE (D.SKILL_CODE & F.CODE) = F.CODE
             )
            THEN 'A'
        WHEN (D.SKILL_CODE & B.CODE) = B.CODE
            THEN 'B'
        WHEN EXISTS (
                 SELECT 1 
                 FROM FRONT F 
                 WHERE (D.SKILL_CODE & F.CODE) = F.CODE
             ) 
            AND (D.SKILL_CODE & P.CODE) != P.CODE
            AND (D.SKILL_CODE & B.CODE) != B.CODE
            THEN 'C'
        ELSE NULL -- A, B, C가 아닐 때 NULL 반환
    END AS GRADE,
    D.ID,
    D.EMAIL
FROM DEVELOPERS D
CROSS JOIN PYTHON P
CROSS JOIN B B
WHERE (
    (D.SKILL_CODE & P.CODE) = P.CODE 
    AND EXISTS (
        SELECT 1 FROM FRONT F WHERE (D.SKILL_CODE & F.CODE) = F.CODE
    )
) 
OR (D.SKILL_CODE & B.CODE) = B.CODE
OR (
    EXISTS (
        SELECT 1 FROM FRONT F WHERE (D.SKILL_CODE & F.CODE) = F.CODE
    )
    AND (D.SKILL_CODE & P.CODE) != P.CODE
    AND (D.SKILL_CODE & B.CODE) != B.CODE
)
ORDER BY GRADE, D.ID;
