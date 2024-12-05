#!/bin/bash

export VS_DRIVER=com.mysql.cj.jdbc.Driver
export VS_USER=root
export VS_PASSWORD=pass
export VS_URL=jdbc:mysql://localhost:3306/VS

java -jar target/voting-system-1.0.jar
