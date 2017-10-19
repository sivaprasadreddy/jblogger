pipeline {
     agent any
     stages {
          stage("Compile") {
               steps {
                    sh "./mvnw clean compile"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./mvnw test"
               }
          }
          stage("Sonar Code Quality Check") {
                steps {
                    sh "./mvnw sonar:sonar"
               }
          }
          stage("Code coverage") {
               steps {
                    /*
                    sh "./gradlew jacocoTestReport"
                    publishHTML (target: [
                         reportDir: 'build/reports/jacoco/test/html',
                         reportFiles: 'index.html',
                         reportName: "JaCoCo Report"
                    ])
                    sh "./gradlew jacocoTestCoverageVerification"
                    */
               }
          }
     }
}
