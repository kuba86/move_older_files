# move_older_files Python

1. Install Python 3.10 from [https://www.python.org/downloads/](https://www.python.org/downloads/)
2. Download git repository
3. Open the directory in Terminal (PowerShell)
2. Create virtual environment
```shell
python -m venv C:\path\to\empty\directory
```
3. Activate virtual environment (example for PowerShell)
```shell
C:\path\to\empty\directory\Scripts\Activate.ps1
```
4. Install requirements.txt with pip
```shell
pip install -r requirements.txt
```
5. Checkout help info
```shell
python move_older_files.py --help
```

Example how to run:

```shell
python move_older_files.py --older_than_in_days=365 --source=C:\syncthing\pictures --destination=C:\backup\phone\pictures
```
