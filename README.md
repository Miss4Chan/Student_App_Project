# Student App

Student App is an open-source Spring Boot web application authored by:
* Despina Misheva 201531
* Marija Stojcheva 201520
* Stefani Kulebanova 201526
* Elena Atanasoska 201525
* Aleksandar Radulovski 201518

mentored by:
* Ph.D Ljupcho Antovski
* Ph.D Petre Lameski
* M.Sc Jana Kuzmanova
* Stefanija Trifunovska 181562

for the purposes of the Software Design and Architecture Course at the Faculty of Computer Science and Engineering in Skopje, winter term 2022/2023.

This application aims to provide a single point of access for all publicly available information regarding many types of facilities in the greater Skopje area. It is directed to help all university students navigate through the city of Skopje with ease, let the students publicly post comments and rate the facilities they have already visited, as well as maintain individual lists of their favourite places. The app is mainly oriented towards students who have spent their freshman and/or sophomore years attending lectures online, will be residing in Skopje this school year for the first time, or are simply in need of a guide for the city's many student-friendly institutions.

More specifically, the application divides facilities in 6 main categories:
* University
* Cafe
* Bakery
* Library
* Supermarket
* Electronics

Each facility is uniquely identified with its name, address, geographical coordinates and category. Additionally, information may include a phone number, website and working hours if the facility provides any. Registered users are free to leave comments as well as rate facilities on a scale of 1-5. 

The user experience is designed with simplicity in mind, keeping the interface as intuitive as possible. The main focus is on the home page and its large-scale, interactive map which provides pins for each facility in the selected category. Upon clicking on a location, the sidebar is opened providing the user with the option to search for a specific facility, detailed information about the selected institution and an option to view comments written by other users regarding it. If the user is authenticated properly, this panel also allows them to write a comment or add the location to their Favourites.

The application is created in Spring Boot framework using Java 17, Maven 4.0.0 and Thymeleaf. It is based on the MVC pattern following the SOLID principle. The locations, that are stored in the database, are extracted from OpenStreetMap and filtered by an unix pipe and filter. Furthermore, the database, made on PostgreSQL, stores users, their comments and favourite locations. The site itself is made to be user-friendly with asynchronious functions communicating with the controllers, to sustain from loading on every update. For a sleek design, the HTML pages are accompanied by Bootstrap 5.0, as well as JavaScript functions to keep it running smoothly.
