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
@Table(name = "comments")
public class CommentEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "content", nullable = false, columnDefinition = "nvarchar(500)")
	private String content;

	@OneToMany(mappedBy = "commentEntity")
	List<CommentImageEntity> commentImageEntities;

	@ManyToOne
	@JoinColumn(name = "user_id")
	UserEntity userEntity;

	@ManyToOne
	@JoinColumn(name = "video_id")
	VideoEntity videoEntity;
}
