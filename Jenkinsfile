node {
    gitlabBuilds(builds: ['1.Checkout', '2.Build', '3.Package', '4.Deploy']) {
        stage('1.Checkout') { // for display purposes
            // Get some code from a GitHub repository
            gitlabCommitStatus('1.Checkout') {
                git credentialsId: 'a8d160f7-10f9-464e-9283-1b1138609fc2', url: 'https://gitlab.com/swd391/etutor_backendspringboot.git'
            }
        }
        stage('2.Build') {
            gitlabCommitStatus('2.Build') {
                sh 'mvn clean install'
            }
        }
        stage('3.Package') {
            gitlabCommitStatus('3.Package') {
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn package'
            }
        }
        if (currentBuild.currentResult == 'SUCCESS') {
            stage('4.Deploy') {
                gitlabCommitStatus('4.Deploy') {
                    sh 'docker rm $(docker stop $(docker ps -a -q -f ancestor=swd391:latest --format="{{.ID}}") 2> /dev/null) 2> /dev/null | docker ps -a'
                    sh 'docker rmi -f swd391:latest'
                    sh 'docker build . -t swd391:latest'
                    sh 'docker run -d -p 8000:8080 swd391:latest'
                }
            }
        }
    }
}