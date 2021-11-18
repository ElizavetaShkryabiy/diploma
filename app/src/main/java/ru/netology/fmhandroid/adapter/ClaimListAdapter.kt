package ru.netology.fmhandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.fmhandroid.R
import ru.netology.fmhandroid.databinding.ItemClaimBinding
import ru.netology.fmhandroid.dto.FullClaim
import ru.netology.fmhandroid.dto.User
import ru.netology.fmhandroid.utils.Utils

interface OnClaimItemClickListener {
    fun onCard(fullClaim: FullClaim) {}
}

class ClaimListAdapter(
    private val onClaimItemClickListener: OnClaimItemClickListener
) : ListAdapter<FullClaim, ClaimListAdapter.ClaimViewHolder>(
    ClaimDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaimViewHolder {
        val binding = ItemClaimBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClaimViewHolder(binding, onClaimItemClickListener)
    }

    override fun onBindViewHolder(holder: ClaimViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    class ClaimViewHolder(
        private val binding: ItemClaimBinding,
        private val onClaimItemClickListener: OnClaimItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(fullClaim: FullClaim) {
            with(binding) {
                val executor = fullClaim.executor
                if (executor != null) {
                    executorNameMaterialTextView.text = Utils.generateShortUserName(
                        executor.lastName!!,
                        executor.firstName!!,
                        executor.middleName!!
                    )
                } else {
                    executorNameMaterialTextView.setText(R.string.not_assigned)
                }

                planTimeMaterialTextView.text = fullClaim
                    .claim
                    .planExecuteDate?.let {
                        Utils.formatTime(
                            it
                        )
                    }
                planDateMaterialTextView.text = fullClaim
                    .claim.planExecuteDate?.let {
                        Utils.formatDate(
                            it
                        )
                    }
                descriptionMaterialTextView.text = fullClaim.claim.title

                claimListCard.setOnClickListener {
                    onClaimItemClickListener.onCard(fullClaim)
                }
            }
        }
    }

    private object ClaimDiffCallback : DiffUtil.ItemCallback<FullClaim>() {
        override fun areItemsTheSame(
            oldItem: FullClaim,
            newItem: FullClaim
        ): Boolean {
            return oldItem.claim.id == newItem.claim.id
        }

        override fun areContentsTheSame(
            oldItem: FullClaim,
            newItem: FullClaim
        ): Boolean {
            return oldItem == newItem
        }
    }

}