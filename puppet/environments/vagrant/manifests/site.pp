
archive { 'spark-1.4.1-bin-hadoop2.6':
  ensure        => present,
  url           => 'http://d3kbcqa49mib13.cloudfront.net/spark-1.4.1-bin-hadoop2.6.tgz',
  timeout       => 2400,
  target        => '/opt',
  extension     => tgz,
  digest_type   => md5,
  digest_string => '858AB5DD5DC0AD4564AFFBB8A777AD47'
}

package { 'java-1.8.0-openjdk-devel':
  ensure => latest
}

class { 'maven::maven':
  version => '3.3.3'
}