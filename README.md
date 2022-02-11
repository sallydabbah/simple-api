# Simple rest-api written in Clojure

API data processor 


## Running locally

in order to run locally, add Clojure repl,that runs under Leiningen.
you can also check tests under test directory.
to start the service , run in Repl : (-main).

## EndPoints 

This service supports 4 GET endpoints:
1. http://localhost:8080/get-unique-visited-sites?visitor-id=<ID>
2. http://localhost:8080/get-num-of-sessions?site-url=<SITE_URL>
3. http://localhost:8080/get-median-session-length?site-url=<SITE_URL>
4. http://localhost:8080/health-check  
  
## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
