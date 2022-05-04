# Navigation Compose Bug Description
This repo demonstrates an issue where if the initial destination is changed on the NavGraph, the
resulting NavBackStackEntry will have a max lifecycle state of STARTED applied to it instead of
RESUMED.

# How to use this
When the application loads, click the Home button and note the state and max lifecycle are set
to STARTED instead of RESUMED.

# Version Information
This project is reproducing the issue with the latest Compose Navigation library version 2.4.2.
With some testing it appears this issue was fixed in 2.5.0-alpha03, but there is no callout as
to why.

# Interesting note
There is an automated instrumentation test that tries to recreate this bug and is unable to.
