SELECT CATEGORY, sum(sales) TOTAL_SALES
FROM BOOK_SALES s
JOIN BOOK b ON b.BOOK_ID = s.BOOK_ID
WHERE s.SALES_DATE >= '2022-01-01' AND s.SALES_DATE < '2022-02-01'
GROUP BY b.CATEGORY 
ORDER BY b.CATEGORY ;