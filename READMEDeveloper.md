# ontll-api Developer



The Best way to write code<br>
<b>1) Utils</b><br>
   ->String null/empty check using `StringUtils.isEmpty()`
     
               if (StringUtils.isEmpty(id)) {
                     throw new FieldValidationException(fieldName + " Should not be empty/null");
                 }
  <br>
   -> Collection empty/null check using  `CollectionUtils.isEmpty()`
    
             if (CollectionUtils.isEmpty((List) objects)) {
                throw new NoRecordFoundException(message);
            }
   <br> 
   Validating UUID: already ValidationUtil class having the method 'validateUUIDFormat(final String id, final String fieldName)'
   call this method and this method valdiate null/
   Example:
        
         // UUID Validation
        final UUID pcidUUID = ValidationUtil.validateUUIDFormat(partcvId, PartcvConstant.PARTCV);
           
   Note: 
        
        PartcvConstant.PARTCV="partcv"
   
    
   
 <b>2)Ugly code vs Clean code</b>
 
 <b>unnecessary creating variable and assignment </b><br>
  Ugly code:
         
         private UUID validUUID(String stringId, String message) {
             UUID id ;
             try {
                 id=UUID.fromString(stringId);
             } catch (final IllegalArgumentException illegalArgumentException) {
                 throw new InvalidUUIDException(message);
             }
             return id;
         }
                  
  clean code
   
        public static UUID validateUUIDFormat(final String id, final String fieldName) {
           try {
               return UUID.fromString(id);
           } catch (final IllegalArgumentException illegalArgumentException) {
               final String errorMessage = CommonHelper.getErrorMessage(
                       ValidationUtilConstant.INVALID,
                       fieldName,
                       ValidationUtilConstant.ID);
               throw new FieldValidationException(errorMessage);
           }
       }
 
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
   1.Create new util.util branch locally and push remote git repository
       
    git clone https://git.daimler.com/ontll/ontll-api.git
    cd ontll-api
    git checkout develop
    git pull
    git checkout -b util.util/<task/storyid>    

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
          
        