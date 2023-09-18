#! /bin/bash

mongoimport --host mongodb --db Quote --collection Quote --type json --file /mongo-seed/quotes.json --jsonArray