# Description

Assignment Count Hash Variable Plugin retrieves the running and completed assignment count for the logged in user.

## Available Formats
Syntax | Description
:----: | -----------
`#loggedInUser.runningCount#` | Returns the running assignment count.
`#loggedInUser.completedCount#` | Returns the completed assignment count.
`#loggedInUser.runningCount[appId=x&processDefId=x&activityDefId=x]#` | Returns the running assignment count, can be filtered and/or by app ID, process definition ID, activity definition ID.<br>Example:<br><sub>#loggedInUser.runningCount[appId=expenseclaim&processDefId=process1&activityDefId=approver_claim]#</sub>
`#loggedInUser.completedCount[appId=x&processDefId=x]#` | Returns the completed assignment count, can be filtered and/or by app ID, process definition ID.<br>Example:<br><sub>#loggedInUser.completedCount[appId=expenseclaim&processDefId=process1]#</sub>

To setup and configure this plugin, please see the [documentation](https://dev.joget.org/community/display/marketplace/User+Assignment+Count+Hash+Variable+Plugin).


# Getting Help

JogetOSS is a community-led team for open source software related to the [Joget](https://www.joget.org) no-code/low-code application platform.
Projects under JogetOSS are community-driven and community-supported.
To obtain support, ask questions, get answers and help others, please participate in the [Community Q&A](https://answers.joget.org/).

# Contributing

This project welcomes contributions and suggestions, please open an issue or create a pull request.

Please note that all interactions fall under our [Code of Conduct](https://github.com/jogetoss/repo-template/blob/main/CODE_OF_CONDUCT.md).

# Licensing

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

NOTE: This software may depend on other packages that may be licensed under different open source licenses.
