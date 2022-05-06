job('example-2-job-DSL') {
	description('DSL Job example for the Jenkins course')
  scm {
    git('https://github.com/japc78/jenkins.job.parametrizado.git', 'main') {
    		node -> 
            node / gitConfigName('japc78')
            node / gitConfigEmail('japc.grafico@gmail.com')
    }
  }
  
  parameters {
    stringParam('NAME', defaultValue = 'Bitcero', description = 'String parameter to Job Boolean')
	choiceParam('PLANET', ['Earth', 'Venus', 'Mars', 'Jupiter'])
    booleanParam('AGENT', false)
   }
  triggers {
    cron('H/7 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('japc.testing@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }    
  }
}
