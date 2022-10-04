# Thales AirLab Assignment

## Github repos

1. Kotlin Springboot Backend (Only implemented RestAPI, Intg Test, CICD on Cloud Run) <br>
   https://github.com/nghaninn/thales-airlab-springboot (this) <br>
   https://thales-airlab-assignment-k32t6nr5uq-de.a.run.app
2. Frontend React (One Page, linked up with Amplify GraphQL) <br>
   https://github.com/nghaninn/thales-airlab-frontend <br>
   URL: https://thales.nghaninn.com/
3. Backend on Amplify (with SQL backend) <br>
   https://github.com/nghaninn/thales-airlab-backend

<br/>

## Steps to start various project

### 1. Kotlin Springboot

Set Environment Variable [thales_apiURL, thales_apiKey] into your system.
>_Was trying to hide secret values, but some issue when implementing it on Cloud Run_

<br>
To spin up locally. <br>

```
docker build -t thales-nghaninn .
docker run -p 8080:8080 -t thales-nghaninn
```

### 2. Frontend React

Build using ant design.
```
yarn install
yarn start
```

### 3. Backend on Amplify

Cannot be executed locally. <br>
Create aws account and download amplify cli to setup and push to cloud. <br>
Manual configure db. <br> 
https://docs.amplify.aws/cli/start/install/

## Database

1. Airport
2. Waypoints
3. SID (Departure Instruction)
4. STAR (Arrival Instruction)
5. SIDWaypoint
6. STARWaypoint

DB query can be found here. 
https://github.com/nghaninn/thales-airlab-backend/blob/main/amplify/tools/default.sql


## Challenges Faced

>1. No Experience with Kotlin Springboot, largely similar to .NET MVC EF framework
>2. Errors with docker scripting
>3. 

<br>

# API needed

1. Download Data: Fetch All Data into DB
    - Airport => https://open-atms.airlab.aero/api/v1/airac/airports
    - Waypoints => https://open-atms.airlab.aero/api/v1/airac/waypoints
    - Iterate Airports
      - Get SID by ICAO => https://open-atms.airlab.aero/api/v1/airac/sids/airport/{icao}
      - Get Star by ICAO => https://open-atms.airlab.aero/api/v1/airac/stars/airport/{icao}
2. List all Airport
3. List Top X waypoints


<br>

# Others
<img src="https://raw.githubusercontent.com/nghaninn/thales-airlab-springboot/main/images/springboot_cicd.png?token=GHSAT0AAAAAABZLZI3HYAR3J7DCGYKIHTQUYZ3I3UA">