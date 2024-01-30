# Generar nueva versión productiva del SDK

For deploying you **must** create a branch named _release/X.Y.Z_ where X.Y is the major and minor number to be published. Once you have created it, push your changes and run the command `fury create-version`.
After the version is generated the .aar file can be obtained in the Nexus Page to be used by the Third Party developers

# Generar documentación de terceros

Run the `./gradlew dokkaHtml dokkaHtml` command and open the `nativesdk/build/dokka/html/index.html` file on your browser

