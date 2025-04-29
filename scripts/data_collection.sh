#!/bin/bash

cleanup() {
    local exit_code=$?
    
    if [ -f "$DOWNLOAD_DIR/${DATASET##*/}.zip" ]; then
        echo "Cleaning up temporary files..."
        rm -f "$DOWNLOAD_DIR/${DATASET##*/}.zip"
    fi
    
    if [ $exit_code -ne 0 ]; then
        exit $exit_code
    fi
}

trap cleanup EXIT

if ! command -v kaggle &> /dev/null; then
    command -v kaggle
    echo "Please install kaggle API via pip"
    exit 1
fi

if [ ! -f ~/.kaggle/kaggle.json ]; then
    echo "Kaggle API credentials not found. Please set them up"
    exit 1
fi

DATASET="currie32/crimes-in-chicago"
DOWNLOAD_DIR=~/project/CriminalChicago/data

mkdir -p "$DOWNLOAD_DIR"

echo "Downloading Chicago Crimes dataset from Kaggle..."

kaggle datasets download -d "$DATASET" -p "$DOWNLOAD_DIR" --unzip

if [ $? -eq 0 ]; then
    echo "Download completed successfully!"
    echo "Files saved in: $DOWNLOAD_DIR/"

    echo -e "\nDownloaded files:"
    ls -lh "$DOWNLOAD_DIR"
else
    echo "Download failed"
    exit 1
fi
