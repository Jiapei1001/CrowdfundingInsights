# CrowdfundingInsights

Hello, there! Welcome to CrowdfundingInsights!

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaJJFY-fOdWrbIgDFlthOYa-_5jsrUD-Exsg&usqp=CAU" width=66% />

Let me introduce myself.


## Value Proposition:
**Crowdfunding Insights** is a web application that provides nonprofits such as [Kiva](https://www.kiva.org/about/) with a detailed analysis of their outreach efforts, lending partners, and recipients. 

This product provides thoughtful analysis of their critical data and tools to help simplify their outreach efforts to partners and borrowers, helping lenders understand their target communities thoughtfully, allowing them to maximize their efforts, make better informed decisions, and help set investment priorities.


## Key Features:
* Allow the user to analyze and filter loan distribution by theme category, region, country, theme, sector, activity, expected duration, and partners.

* Provide the user with poverty level based lending suggestions by analyzing the multidimensional poverty index(MPI) of regions and external poverty resources including:
	*  [**World Development Indicators**](https://datacatalog.worldbank.org/dataset/world-development-indicators/)
	*  [**Prevalence of Unemployment**](https://datatopics.worldbank.org/jobs/)

* Allow the user to analyze the geolocations of the loan distribution and needs per theme category, activity, borrowing intensity (amount, frequency).

* Provide recommendations on what lender Kiva should approach with a funding need based on a given region, amount, expected duration of the loan, and category.


## Perspectives:
The responsive web application is capable of communicating with backend SQL database. By searching from keywords, it pulls data from the relational data model. From there, it drives further comparative analysis by traverse the primary keys through the data models, and visualize data outcomes through tables, charts, or geographical maps. 


#### From Loans:
![](https://drive.google.com/uc?export=view&id=120iF_r48pwQYiIDJXltT3R3V38_lGr5S)
<br>
<br>
#### From Loan Partners:
![](https://drive.google.com/uc?export=view&id=1wkRIIG5gCZgov8PhoH0gSD-8v0gx5JQd)
<br>
<br>
#### From Loan Themes:
![](https://drive.google.com/uc?export=view&id=1B31KhFpD4LsaSRkbGzn1NuMzLHvN3ngP)



## Data Warehousing:

### External Data Source:

* Dataset 1: [**World Development Indicators**](https://datacatalog.worldbank.org/dataset/world-development-indicators/)

	This is a CSV file obtained from The World Bank, specifically from the world development indicators database. This dataset includes information on various metrics covering economy and growth, education, and environment. With the variety of indicators included in this dataset we plan to investigate whether access to financial institutions, education, etc. correlates to funding amount. Looking at these metrics will also help us to provide recommendations to Kiva with regards to where the need for more funding may be the greatest.



* Dataset 2: [**Prevalence of Unemployment**](https://datatopics.worldbank.org/jobs/)

	In this data set, we look at the prevalence of unemployment in a country. The numbers are given as a percentage as the total workforce. Our reason for investigating the unemployment rate is that small businesses are at a higher risk of going under, and that we expect to see a correlation between loans requested and unemployment. Although the dataset included many years worth of data, we chose to only look at 2018.

### ETL Workflow #1: 
##### Average (2016- 2018) Percentage of Population with an Account at a Financial Institution By Country

<img src="https://drive.google.com/uc?export=view&id=1Yuz3m4mBGvYl4VPqUdXHKYKUyg9gjJrj" width=66% />

<img src="https://drive.google.com/uc?export=view&id=1pXWrPmtCtJ4aZLxGsMy2YHefKph01aJC" width=66% />

<img src="https://drive.google.com/uc?export=view&id=1yVgUXCtKo6bQQ5Q_UdqTavd8Sg3nsmpE" width=66% />


### ETL Workflow #2: 
##### Average (2016- 2018) Percentage of Population with an Account at a Financial Institution By Region


<img src="https://drive.google.com/uc?export=view&id=133SI5IH44dPL53KGWCH7hTcSyb37lP3I" width=66% />

<img src="https://drive.google.com/uc?export=view&id=1lWYU9Y73_NpwDZWZDM8A2NTTkJ1G1nrP" width=66% />


### ETL Workflow #3: 
##### School Enrollment


<img src="https://drive.google.com/uc?export=view&id=1qKicN3_kJptD7VZntI0ViT0Y7opDws_K" width=66% />

<img src="https://drive.google.com/uc?export=view&id=19tfjARyV0Hc2jQrDuaSF3v9ZxC8wu52y" width=66% />


### ETL Workflow #4: 
##### Average (2016-2018) Loan Amount Distribution and GDP by Country
<img src="https://drive.google.com/uc?export=view&id=1-5iCIJmx3G7pe4wa0FhehOFH_I1jONq-" width=66% />

<center>Average (2016-2018) Loan Amount Distribution by Country</center>

<br>
<br>

<img src="https://drive.google.com/uc?export=view&id=13SQW52nt--7CRY-Iwsp5FHgC-t1acJOa" width=66% />
<center>Average (2016-2018) GDP by Country</center>
