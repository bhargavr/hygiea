package com.sjsu.hygiea.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.hygiea.dao.ClusterDao;
import com.sjsu.hygiea.dto.Cluster;


//import org.springframework.core.env.Environment;
@RestController
public class FetchModelController
{

	private final ClusterDao clusterDao;

	@Inject
	public FetchModelController(final ClusterDao clusterDao)
	{
		this.clusterDao = clusterDao;
	}

	@RequestMapping("/fetchClusters")
	public List<Cluster> fetchClusters()
	{
		final List<Cluster> clusters = clusterDao.getClusters();
		return clusters;
	}


}
