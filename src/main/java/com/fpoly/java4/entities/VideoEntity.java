package com.fpoly.java4.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
public class VideoEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false, columnDefinition = "nvarchar(500)")
	private String name;

	@Column(name = "descriptions", nullable = false, columnDefinition = "nvarchar(500)")
	private String desc;

	@Column(name = "image", nullable = false, length = 200)
	private String image;

	@Column(name = "video_url", nullable = false, length = 200)
	private String videoURL;

	@Column(name = "view_count", nullable = false)
	private int viewCount;

	@Column(name = "status", nullable = false)
	private int status;

	@ManyToOne
	@JoinColumn(name = "cat_id")
	private CategoryEntity categoryEntity;

	@OneToMany(mappedBy = "videoEntity")
	List<FavouriteEntity> favouritesEntities;

	@OneToMany(mappedBy = "videoEntity")
	List<CommentEntity> commentEntities;
}
