# DELD-demos

A quarkus and a spring boot project utilizing [DELD-httpclient](https://github.com/malandrakisgeo/DELD-httpclient)

They communicate with one another. Run the Spring project first, and then run quarkus, because the latter automatically sends several requests to the former right after starting. 

They are very crudely made, but serve their purpose.

Make sure you've cloned and build DELD before running.

The spring project should be deployed on a server (e.g. Tomcat) in order for the Asynchronous responses to properly work. Running directly via e.g. IntelliJ won't do the trick.
