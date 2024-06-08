#!/bin/bash
rutaBorrado="./temp"
patronBorrado="*.tmp"
antiguedadBorrado="120" # Tiempo en minutos
directorioBorrado="carpeta"

find $rutaBorrado/$patronBorrado -type f -mmin $antiguedadBorrado -execdir rm {} \; >/dev/null 2>&1
find $rutaBorrado/$directorioBorrado -type d -mmin $antiguedadBorrado -execdir rm -rf {} \; >/dev/null 2>&1

#Borrado de directorios
for i in {a..z}
do
  find /tmp -name "tti__$i*" -type d -mmin +240 -execdir rm -rf {} \; >/dev/null 2>&1
done
for i in {0..9}
do
  find /tmp/ -name "tti__$i*" -type d -mmin +240 -execdir rm -rf {} \; >/dev/null 2>&1
done
find /tmp/ -name "tti_*" -type d -mmin +240 -execdir rm -rf {} \; >/dev/null 2>&1



