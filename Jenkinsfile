pipeline {
    agent any
    stages {
        stage('Start of Pipeline') {
            steps {
                echo 'Start of Pipeline'
            }
        }
    stage('Pull Project from REPO') {
            steps {
                build job: "RepoJob", wait: true
            }
        }
	stage('Execute GBP Scorer Regression TestSuite') {
            steps {
                build job: "SEOR_AgencyTool_GBPScorer_TestSuite", wait: true
            }
        }
	stage('Execute Web Audit Regression TestSuite') {
            steps {
                build job: "SEOR_AgencyTool_WebAudit_TestSuite", wait: true
            }
        }
    stage('Execute Lead Finder Regression TestSuite') {
            steps {
                build job: "SEOR_AgencyTool_LeadFinder_TestSuite", wait: true
            }
        }
    stage('End of Pipeline') {
            steps {
                echo 'End of Pipeline'
            }
        }
    }
}
