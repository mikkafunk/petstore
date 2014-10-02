SELECT
   name, value
FROM
   information_schema.settings 
ORDER BY 
   UPPER(name)