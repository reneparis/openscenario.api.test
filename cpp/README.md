OpenSCENARIO
============

## C++
**Note:**
The Linux and Windows (post) build steps are still under development and work in progress!  Post build step for Windows is not yet implemented.

### Linux and Windows
* Clone the master branch (the `cpp` path from this project).
* Make sure you have `cmake` installed.

### Linux
##### System requirements:
- gcc >= 5.0
- cmake >= 3.8
- SSD/HDD free space >= 1.5 GB
- Main memory >= 8 GB
If you have 8 GB then please change the last line in the script `generateLinux.sh` from `make -j8` to `make`. 

##### Build steps:
* Install `uuid-dev` (needed for building antlr4):
```bash
$ sudo apt install uuid-dev
```
* Change your current directory to `cpp/build/generateLinux` and execute:
```bash
$ ./generateLinux.sh Release make
```
* This creates all necessary makefiles for building the openSCENARIO library as shared library and starts the compilation process by executing `make -j8` starting 8 build threads. If you have 8 GB then change the make command to `make` only (without the `-j8` parameter) as described above in the paragraph **"System requirements"**.
* The general call to the script above is `./generateLinux.sh [Release|Debug] [static|shared] [make]`. For example the command give above will build the OpenSCENARIO shared libraries with an example implementation in the following directory structure:
```bash
📦cpp
 ┣ 📂build
 ┃ ┣ 📂generateLinux
 ┃ ┃ ┣ 📂gccLinuxSharedRelease
 ┃ ┃ ┃   Here are the makefiles located to build the libraries and binaries.
 ┃ ┗ 📂output
 ┃ ┃ ┣ 📂LinuxShared
 ┃ ┃ ┃ ┗ 📂Release
 ┃ ┃ ┃ ┃   Here are the libraries and binaries.
 ┃ ┃ ┃ ┃ ┣ 📂examples
 ┃ ┃ ┃ ┃ ┃   This folder containing the example files.
```
 * To create a package containing all necessary include files and binaries (libraries) execute the bash script below. A file named `openSCENARIO_<date>.tgz` will be created.
```bash
$ ./createLinuxBinPackage.sh
```

### Windows
* Make sure you have Visual Studio 2015 or 2017 installed.
* Open a file explorer and navigate to your `openSCENARIO` folder and there to `cpp/build/generateWindows`.
* Execute `generate_vs20[15|17]_x64].bat` according to your Visual Studio installation and a Visual Studio solution in the folder `VS20[15|17][_x64]` will be created.
* Inside that folder open the solution `OpenScenario-Cpp.sln` and build the project.
* The created binaries are then located in the folder `cpp/build/output/[Win32|x64]`.
