#!bin/bash
# 1-x, 2-y, 3-typeOfAmenity, 4-name, 5-address, 6-phone, 7-web page, 8-opening hours
echo "X,Y,TypeOfAmenity,Name,Address,Phone,WebPage,OpeningHours" >> out.csv
cat education.csv | awk -F, '{if ($3 == "university" || $3 == "library") print $1 "," $2"," $3"," $4"," $5"," $14"," $15","}' >> out.csv #noOpeningHours
cat cafes.csv | awk -F, '{if($4 != "" && $4 != "name") print $1 "," $2"," $3"," $4"," $8 "," $15 "," $18","$9}' >> out.csv
cat shop_food.csv | awk -F, '{if($4 != "" && $4!="name") print $1","$2","$3","$4","$5","$15","$10","$8}' >> out.csv
cat pub.csv | awk -F, '{if($4 != "" && $4!="name") print $1","$2","$3","$4","$7","$37","$19","$9}' >> out.csv #noPhone left empty
cat shop_general.csv | awk -F, '{if($4 != "" && $4 !="name") print $1","$2","$3","$4","$5","$14","$9","$8}' >> out.csv
cat shop_electronics.csv | awk -F, '{if($3!="" && $3!="name") print $1","$2","$4","$3","$6","$11","$12","$8}' >> out.csv
cat entertainment.csv | awk -F, '{if($4!="" && $4!="name") print $1","$2","$3","$4","$12","$17","$13","$16}' >> out.csv
