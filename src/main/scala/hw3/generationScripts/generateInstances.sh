

# Params:
# N - 500
# n - 24
# W - 5000
# C - 5000
# correlation - none
# weight balance - balanced

N=500
n=30
W=5000
C=5000
w=bal
c=uni
m=0.8

outputPathRatio="../../../resources/HW3/RatioCapacityToWeight"
outputPathCorr="../../../resources/HW3/CorrelationPriceWeight"
outputPathDistr="../../../resources/HW3/WeightDistribution"
outputPathLight="../../../resources/HW3/GranularityLight"
outputPathHeavy="../../../resources/HW3/GranularityHeavy"
outputPathWeight="../../../resources/HW3/MaxWeight"
outputPathCost="../../../resources/HW3/MaxCost"
outputPathNormal="../../../resources/HW3/Normal"


mkdir -p "$outputPathRatio"
mkdir -p "$outputPathCorr"
mkdir -p "$outputPathDistr"
mkdir -p "$outputPathLight"
mkdir -p "$outputPathHeavy"
mkdir -p "$outputPathWeight"
mkdir -p "$outputPathNormal"



# Max weight
../generator/kg2 -n $n -N $N -W 100 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight100"
../generator/kg2 -n $n -N $N -W 300 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight300"
../generator/kg2 -n $n -N $N -W 500 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight500"
../generator/kg2 -n $n -N $N -W 700 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight700"
../generator/kg2 -n $n -N $N -W 1000 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight1000"
../generator/kg2 -n $n -N $N -W 1500 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight1500"
../generator/kg2 -n $n -N $N -W 3000 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight3000"
../generator/kg2 -n $n -N $N -W 5000 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight5000"
../generator/kg2 -n $n -N $N -W 10000 -C $C -w $w -c $c -m $m > "$outputPathWeight/weight10000"


# Max cost
../generator/kg2 -n $n -N $N -W $W -C 100 -w $w -c $c -m $m > "$outputPathCost/cost100"
../generator/kg2 -n $n -N $N -W $W -C 300 -w $w -c $c -m $m > "$outputPathCost/cost300"
../generator/kg2 -n $n -N $N -W $W -C 500 -w $w -c $c -m $m > "$outputPathCost/cost500"
../generator/kg2 -n $n -N $N -W $W -C 700 -w $w -c $c -m $m > "$outputPathCost/cost700"
../generator/kg2 -n $n -N $N -W $W -C 1000 -w $w -c $c -m $m > "$outputPathCost/cost1000"
../generator/kg2 -n $n -N $N -W $W -C 1500 -w $w -c $c -m $m > "$outputPathCost/cost1500"
../generator/kg2 -n $n -N $N -W $W -C 3000 -w $w -c $c -m $m > "$outputPathCost/cost3000"
../generator/kg2 -n $n -N $N -W $W -C 5000 -w $w -c $c -m $m > "$outputPathCost/cost5000"
../generator/kg2 -n $n -N $N -W $W -C 10000 -w $w -c $c -m $m > "$outputPathCost/cost10000"


# Ratio
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 0.1 > "$outputPathRatio/ratio01"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 0.3 > "$outputPathRatio/ratio03"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 0.5 > "$outputPathRatio/ratio05"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 0.7 > "$outputPathRatio/ratio07"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 0.9 > "$outputPathRatio/ratio09"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 1 > "$outputPathRatio/ratio1"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 1.5 > "$outputPathRatio/ratio15"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 3 > "$outputPathRatio/ratio3"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m 5 > "$outputPathRatio/ratio5"


# Correlation
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c uni -m $m > "$outputPathCorr/uni"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c corr -m $m > "$outputPathCorr/corr"
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c uni -m $m > "$outputPathCorr/bal"


# Distribution
../generator/kg2 -n $n -N $N -W $W -C $C -w bal -c $c -m $m > "$outputPathDistr/bal"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m > "$outputPathDistr/light"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m > "$outputPathDistr/heavy"


# Light granularity
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 0 > "$outputPathLight/light0"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 0.3 > "$outputPathLight/light03"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 0.5 > "$outputPathLight/light05"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 0.7 > "$outputPathLight/light07"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 1 > "$outputPathLight/light1"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 1.5 > "$outputPathLight/light15"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 3 > "$outputPathLight/light3"
../generator/kg2 -n $n -N $N -W $W -C $C -w light -c $c -m $m -k 5 > "$outputPathLight/light5"


# Heavy granularity
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 0 > "$outputPathHeavy/heavy0"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 0.3 > "$outputPathHeavy/heavy03"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 0.5 > "$outputPathHeavy/heavy05"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 0.7 > "$outputPathHeavy/heavy07"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 1 > "$outputPathHeavy/heavy1"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 1.5 > "$outputPathHeavy/heavy15"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 3 > "$outputPathHeavy/heavy3"
../generator/kg2 -n $n -N $N -W $W -C $C -w heavy -c $c -m $m -k 5 > "$outputPathHeavy/heavy5"


# Normal
../generator/kg2 -n $n -N $N -W $W -C $C -w $w -c $c -m $m > "$outputPathNormal/normal"
