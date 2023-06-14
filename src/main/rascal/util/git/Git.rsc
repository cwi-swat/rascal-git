@license{
BSD 2-Clause License

Copyright (c) 2022, Mark Hills

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
}
@synopsis{Access functions to git features via JGit}
@description{
This library is a growing collection of functions that
enable Rascal programmers to interact with the git version
management system.
}
module util::git::Git
    
@javaClass{edu.appstate.cs.rascalgit.RascalGit}
@synopsis{`git clone`}
@description{
This has the effect `git clone`, and it registers this repository
with rascal-git for further processing.
}
java void cloneRemoteRepository(str remotePath, loc localPath);

@javaClass{edu.appstate.cs.rascalgit.RascalGit}
@description{
This registers an existing clone with rascal-git for further processing.
}
java void openLocalRepository(loc localPath);

@javaClass{edu.appstate.cs.rascalgit.RascalGit}
@synopsis{`git tag --list`}
java list[str] getTags(loc repoPath);

@javaClass{edu.appstate.cs.rascalgit.RascalGit}
@synopsis{`git checkout myTagName`}
java void switchToTag(loc repoPath, str \tag);

@javaClass{edu.appstate.cs.rascalgit.RascalGit}
@synopsis{`git log -1 --format=%ai myTagName`}
java datetime getTagCommitDate(loc repoPath, str \tag);
