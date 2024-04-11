#!/bin/bash

# Database details
DB_USER=project
DB_PASSWORD=project
DB_SID=XE

# Backup directory
BACKUP_DIR="/h/casestudy/bash"

# Timestamp for the backup file
TIMESTAMP=$(date +%Y%m%d%H%M%S)

# Ensure the backup directory exists
mkdir -p "$BACKUP_DIR"

# Backup the Oracle database using expdp
expdp "$DB_USER/$DB_PASSWORD@$DB_SID" directory=DATA_PUMP_DIR dumpfile=db_backup_$TIMESTAMP.dmp logfile=db_backup_$TIMESTAMP.log full=y

# Check if the backup was successful
if [ $? -eq 0 ]; then
    echo "Oracle database backup completed successfully. File: db_backup_$TIMESTAMP.dmp"
else
    echo "Oracle database backup failed."
fi
