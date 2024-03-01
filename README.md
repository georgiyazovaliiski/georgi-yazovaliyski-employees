# georgi-yazovaliyski-employees
An interview task about CSV file reading and data analysis of partnering employees. 

**This task was implemented in two steps:**
1) Build CSV reading and duo sorting process in a Java Backend. It runs on port 8080.
2) Build a user interface in React.js and communicate with the backend via Http requests. It runs on port 3000.
* I hesitated whether to use Thymeleaf vs React since the task stated the solution should be in Java, but proceeded with React UI because it seemed like the more modern, seamless solution.

**How to run:**

1) cd into the cloned repository

**Running the backend**

2) cd .\employees\
3) mvn clean install

**Go back to the root of the repository**

4) open another terminal in the cloned repository

**Running the frontend**

5) cd .\employees-front-end\
6) npm install
7) npm start

**How to use:**

1) Start backend
2) Start frontend
3) Choose a file from your file system
4) Click submit
5) Look at a table of the longest partnering employee duo

*If there is any error with the whole process, it will be displayed for one to analyze and debug. Maybe the file you are submitting has typos, formatting mistakes, etc.
