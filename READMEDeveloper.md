# User Tracking 
   <br>
    <b>magic number</b>
    
    public class Foo {
        public void setPassword(String password) {
             // don't do this
             if (password.length() > 7) {
                  throw new InvalidArgumentException("password");
             }
        }
    }
    
   It improves readability of the code and it's easier to maintain.
   
    public class Foo {
        public static final int MAX_PASSWORD_SIZE = 7;
    
        public void setPassword(String password) {
             if (password.length() > MAX_PASSWORD_SIZE) {
                  throw new InvalidArgumentException("password");
             }
        }
    }
   Note:
    The JDK is full of examples like in Integer, Character and Math classes.
  
  Intellij-Shortcut:
   1.Upper case
     
     'ctrl+shif+u'
   2.Find the caller of method
   
    'ctrl+Alt+H'
   
   SonarQube
     
     mvn sonar:sonar -Dsonar.host.url=http://localhost:9000
     
   Git merge:   

   2.Push the new util.util branch
      
      git add .         (. means committing all file)
      git commit -m "<your message>"
      git push --set-upstream origin util.util/<task/storyid>
     
      
   3.Develop to feature(Merge)
     
     git checkout develop    #if you don't have it already
     git checkout feature/x  #if you don't have it already
     git pull --all
     git merge develop
   
   
   4.Discard all local changes to all files permanently:
        
        git reset --hard <file-name>
        
   5. Discard recenet commits:
   
        
        git reset --soft HEAD~1
      
  
   Commit message edit
   
      git commit --amend and press Enter.
      
      :wq
       
       

       
Kill 8080 port on cmd

        netstat -ano | findstr 8080
        
        taskkill /F /PID  16240
  Maven
  
      mvn clean install -Dmaven.test.skip=true  
      mvn verify
      mvn clean install -Powasp-depency-check -Dmaven.test.skip=true
          
        
