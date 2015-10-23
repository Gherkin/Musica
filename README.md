# Musica
A simple Java-EE lab!

### Dependencies
MySQL

Tomcat (or any other container)

### Installation
`$ mysql -u root -p < basedir/src/main/sql/CreateDatabase.sql`

### Building
`$ mvn war:war`

If using Tomcat, add a server to mavens settings xml with the id *TomcatServer*
```
<server>
  <id>TomcatServer</id>
  <username>{yourusername}</username>
  <password>{yourpassword}</password>
</server>
```
The user needs to have the *manager-script* role in Tomcat, defined in `TomcatDir/conf/tomcat-users.xml`
Then simply deploy to the running Tomcat server using

`$ mvn tomcat7:deploy`
