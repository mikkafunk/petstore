<#-- @ftlvariable name="" type="funk.shane.pets.views.DatabaseView" -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="/assets/pets.css">
    </head>
    
    <body>
        <h1>H2 Settings</h1>
        Demo of Dropwizard including my customized data source (H2) connector
        <table>
        <#list dataMap?keys as item>
          <tr>
            <td>${item}</td>
            <#if dataMap[item]??>
            <td>${dataMap[item]}</td>
            <#else>
            <td>&nbsp;</td>
            </#if>
          </tr>
        </#list>
        </table>
    </body>
</html>