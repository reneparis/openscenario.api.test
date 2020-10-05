#!/bin/bash


################################################################
# setup some variables
################################################################
# get script folder
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
# cd to script dir
cd ${SCRIPT_DIR}
# add colors
. ${SCRIPT_DIR}/colors.sh
# set OpenSCENARIO API folder
OPEN_SCEANARIO_API=openScenario.v1_0.API
# prepare OpenSCENARIO API folder
mkdir -p "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}"
rm -rf "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}"/*


################################################################
# prepare includes
################################################################
# collect headers
STEP=1
echo -e "${GREEN}Step ${STEP}: Collecting headers${WHITE}"
${SCRIPT_DIR}/collectHeaderFiles.sh ${OPEN_SCEANARIO_API}


################################################################
# prepare CMakeLists.txt
################################################################
echo
STEP=`expr ${STEP} + 1`
echo -e "${GREEN}Step ${STEP}: Preparing CMakeLists.txt${WHITE}"
cp -f "${SCRIPT_DIR}/../../CMakeHelpers.cmake" "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}"
"${SCRIPT_DIR}/createCMakeListsTemplate.sh" "${OPEN_SCEANARIO_API}"


################################################################
# prepare lib files
################################################################
# check if libraries are already compiled
echo
STEP=`expr ${STEP} + 1`
echo -e "${GREEN}Step ${STEP}: Checking for compiled libraries${WHITE}"
FOLDERS_TO_COPY=
for j in Shared Static ; do
	for i in Linux Win32 x64 ; do
        for k in Release Debug ; do
            if [ -d "${SCRIPT_DIR}/../output/${i}${j}/${k}" ] ; then
                if [ -f "${SCRIPT_DIR}/../output/${i}${j}/${k}/libantlr4-runtime.a" ] || [ -f "${SCRIPT_DIR}/../output/${i}${j}/${k}/antlr4-runtime.lib" ] ; then
                    FOLDERS_TO_COPY="${FOLDERS_TO_COPY}${i}${j}/${k}:${j}/${i}/${k} "
                    echo "Libraries available as ${k} for ${i}${j}"
                fi
            fi
        done
    done
done

if [ -z "${FOLDERS_TO_COPY}" ] ; then
    echo -e "${RED}Error: Did not find any libraries.${WHITE}"
    echo -e "Please run for example './generateLinux.sh make Release shared' to compile the OpenSCENARIO libraries.${WHITE}"
    exit -1
fi

# create lib folders
echo
STEP=`expr ${STEP} + 1`
echo -e "${GREEN}Step ${STEP}: Creating library folders for Linux and Windows${WHITE}"
PREV_TARGET_FOLDER=
for FOLDER in ${FOLDERS_TO_COPY} ; do
    TARGET_FOLDER=`echo ${FOLDER} | cut -d : -f 2`
    if [[ "${PREV_TARGET_FOLDER}" == *"${TARGET_FOLDER}"* ]] ; then
        continue
    fi
    PREV_TARGET_FOLDER="${PREV_TARGET_FOLDER}${TARGET_FOLDER} "
    echo "Creating folder ${TARGET_FOLDER}"
    mkdir -p "${OPEN_SCEANARIO_API}/lib/${TARGET_FOLDER}"
    if [ $? -ne 0 ] ; then echo -e "${RED}Errors occurred!${WHITE}" ; fi
done

# copy libs
echo
STEP=`expr ${STEP} + 1`
echo -e "${GREEN}Step ${STEP}: Copying libraries to Linux and Windows folders${WHITE}"
for FOLDER in ${FOLDERS_TO_COPY} ; do
    SOURCE_FOLDER=`echo ${FOLDER} | cut -d : -f 1`
    TARGET_FOLDER=`echo ${FOLDER} | cut -d : -f 2`
    echo "Copying data from <${SOURCE_FOLDER}> to <${TARGET_FOLDER}>"
    if [[ "${SOURCE_FOLDER}" == "Linux"* ]] ; then
        cp -r "${SCRIPT_DIR}/../output/${SOURCE_FOLDER}"/lib* "${OPEN_SCEANARIO_API}/lib/${TARGET_FOLDER}"
    else
        cp -r "${SCRIPT_DIR}/../output/${SOURCE_FOLDER}"/*.lib "${OPEN_SCEANARIO_API}/lib/${TARGET_FOLDER}"
        cp -r "${SCRIPT_DIR}/../output/${SOURCE_FOLDER}"/*.dll "${OPEN_SCEANARIO_API}/lib/${TARGET_FOLDER}"
        cp -r "${SCRIPT_DIR}/../output/${SOURCE_FOLDER}"/*.exp "${OPEN_SCEANARIO_API}/lib/${TARGET_FOLDER}"
    fi
    if [ $? -ne 0 ] ; then echo -e "${RED}Errors occurred!${WHITE}" ; fi
done

# strip debug infos
#strip "${OPEN_SCEANARIO_API}"/lib/*


################################################################
# copy demo application
################################################################
echo
STEP=`expr ${STEP} + 1`
mkdir -p "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}/src/TinyXML2"
echo -e "${GREEN}Step ${STEP}: Copying demo application${WHITE}"
cp "../../applications/openScenarioReader/v1_0/src/OpenScenarioReader.cpp" "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}/src"
cp "../../applications/openScenarioReader/v1_0/src/Version.h" "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}/src"
cp "../../externalLibs/TinyXML2/tinyxml2.cpp" "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}/src/TinyXML2"


################################################################
# copy examples
################################################################
echo
STEP=`expr ${STEP} + 1`
echo -e "${GREEN}Step ${STEP}: Copying examples files${WHITE}"
cp -r "${SCRIPT_DIR}/../../../doc/examples" "${SCRIPT_DIR}/${OPEN_SCEANARIO_API}"


################################################################
# create package
################################################################
#CUR_DATE=`date '+%Y.%m.%d'`
#echo
#STEP=`expr ${STEP} + 1`
#echo -e "${GREEN}Step ${STEP}: Creating package <${OPEN_SCEANARIO_API}_${CUR_DATE}.tgz>${WHITE}"
## tar and gzip
#tar -zcf "${OPEN_SCEANARIO_API}_${CUR_DATE}.tgz" "${OPEN_SCEANARIO_API}"

echo
echo -e "${GREEN}Finished creating package!${WHITE}"
