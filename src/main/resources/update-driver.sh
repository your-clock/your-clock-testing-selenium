#!/bin/bash

DRIVERS_DIRECTORY="src/main/resources/drivers"

if [[ "$OSTYPE" == "darwin"* ]]; then
    OS_PLATFORM="mac"

    VERSION_FULL_EDGE_BROWSER=$(/Applications/Microsoft\ Edge.app/Contents/MacOS/Microsoft\ Edge --version | tr -s ' ' '.' | cut -d "." -f 3,4,5,6)
    echo "Current version of Edge is: $VERSION_FULL_EDGE_BROWSER"
    VERSION_EDGE_DRIVER="$($DRIVERS_DIRECTORY/msedgedriver -v | tr -s ' ' '.' | cut -d "." -f 4,5,6,7)"
    echo "Version of EdgeDriver is: $VERSION_EDGE_DRIVER"

    VERSION_FULL_CHROME_BROWSER=$(/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --version | tr -s ' ' '.' | cut -d "." -f 3,4,5,6)
    echo "Current version of Chrome is: $VERSION_FULL_CHROME_BROWSER"
    VERSION_CHROME_DRIVER="$($DRIVERS_DIRECTORY/chromedriver -v | tr -s ' ' '.' | cut -d "." -f 2,3,4,5)"
    echo "Version of ChromeDriver is: $VERSION_CHROME_DRIVER"
else
    OS_PLATFORM="linux"

    VERSION_FULL_CHROME_BROWSER=$(google-chrome --version | tr -s ' ' '.' | cut -d "." -f 3,4,5,6)
    echo "Current version of Chrome is: $VERSION_FULL_CHROME_BROWSER"
    VERSION_CHROME_DRIVER="$DRIVERS_DIRECTORY/chromedriver -v | tr -s ' ' '.' | cut -d "." -f 2,3,4,5)"
    echo "Version of ChromeDriver is: $VERSION_CHROME_DRIVER"
fi

if [ "$VERSION_FULL_EDGE_BROWSER" != "$VERSION_EDGE_DRIVER" ]; then
    echo $(wget -P $DRIVERS_DIRECTORY https://msedgedriver.azureedge.net/$VERSION_FULL_EDGE_BROWSER/edgedriver_$OS_PLATFORM\64.zip)
    echo $(unzip -o $DRIVERS_DIRECTORY/edgedriver_$OS_PLATFORM\64.zip -d $DRIVERS_DIRECTORY)
    echo $(rm -r $DRIVERS_DIRECTORY/Driver_Notes && rm -r $DRIVERS_DIRECTORY/edgedriver_$OS_PLATFORM\64.zip)
fi
if [ "$VERSION_FULL_CHROME_BROWSER" != "$VERSION_CHROME_DRIVER" ]; then
    echo $(wget -P $DRIVERS_DIRECTORY https://chromedriver.storage.googleapis.com/$VERSION_FULL_CHROME_BROWSER/chromedriver_$OS_PLATFORM\64.zip)
    echo $(unzip -o $DRIVERS_DIRECTORY/chromedriver_$OS_PLATFORM\64.zip -d $DRIVERS_DIRECTORY)
    echo $(rm -r $DRIVERS_DIRECTORY/chromedriver_$OS_PLATFORM\64.zip)
fi
