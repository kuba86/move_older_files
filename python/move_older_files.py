#  Copyright 2022 kuba86
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

import os
import pendulum
import shutil
import argparse

parser = argparse.ArgumentParser()

parser.add_argument('--older_than_in_days', help='How old a file should be, to be moved to a backup directory')
parser.add_argument('--source', help='Path to where the files will be scanned and moved from')
parser.add_argument('--destination', help='Path where files will be moved to (where you would like to backup the files)')

args = parser.parse_args()

list_of_files_to_move = []
list_of_files_to_leave = []

for directory_path, directory_names, filenames in os.walk(args.source):
    if os.path.basename(directory_path).startswith('.'):
        print(f'Directory {os.path.basename(directory_path)} starts with a dot (.) and will be skipped.')
    else:
        for file in filenames:
            full_file_path = os.path.join(directory_path, file)

            full_file_statistics = os.stat(full_file_path)
            file_modified_time = pendulum.from_timestamp(full_file_statistics.st_mtime)
            file_create_time = pendulum.from_timestamp(full_file_statistics.st_ctime)
            current_time = pendulum.now()
            diff_now_and_file_modified_time = file_create_time.diff(file_modified_time).in_days()

            if diff_now_and_file_modified_time > int(args.older_than_in_days):
                list_of_files_to_move.append(full_file_path)
            else:
                list_of_files_to_leave.append(full_file_path)

for file in list_of_files_to_move:
    file_relative_path_to_source = os.path.relpath(file, args.source)
    destination_file_with_path = os.path.join(args.destination, file_relative_path_to_source)
    destination_directory_of_the_file = os.path.dirname(destination_file_with_path)
    os.makedirs(destination_directory_of_the_file, exist_ok=True)

    if os.path.exists(destination_file_with_path):
        print(f'file {destination_file_with_path} already exists. It will NOT be overwritten.')
    else:
        shutil.move(file, destination_file_with_path)
