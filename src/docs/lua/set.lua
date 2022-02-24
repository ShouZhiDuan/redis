for i = 1, 10000000
do
   redis.call("set",i,i)
end
return "OK"
